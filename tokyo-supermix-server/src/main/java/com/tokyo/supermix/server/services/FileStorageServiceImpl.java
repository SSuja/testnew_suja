package com.tokyo.supermix.server.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.opencsv.CSVReader;
import com.tokyo.supermix.config.FileStorageProperties;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.MaterialState;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import com.tokyo.supermix.data.repositories.DesignationRepository;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.EquipmentRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialStateRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.ProjectRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.SupplierCategoryRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;
import com.tokyo.supermix.data.repositories.UnitRepository;
import com.tokyo.supermix.rest.exception.TokyoSupermixFileNotFoundException;
import com.tokyo.supermix.rest.exception.TokyoSupermixFileStorageException;

@Service
public class FileStorageServiceImpl implements FileStorageService {
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private UnitRepository unitRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private SupplierCategoryRepository supplierCategoryRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private DesignationRepository designationRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private PlantEquipmentRepository plantEquipmentRepository;
  @Autowired
  private EquipmentRepository equipmentRepository;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private MaterialStateRepository materialStateRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
  @Autowired
  private RawMaterialService rawMaterialService;
  @Autowired
  private FileStorageProperties fileStorageProperties;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;

  private final Path fileStorageLocation;

  @Autowired
  public FileStorageServiceImpl(FileStorageProperties fileStorageProperties)
      throws TokyoSupermixFileStorageException {
    this.fileStorageLocation =
        Paths.get(fileStorageProperties.getUploadDir1()).toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new TokyoSupermixFileStorageException(
          "Could not create the directory where the uploaded files will be stored", ex);
    }
  }

  @Transactional
  public void uploadCsv(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    try {
      Files.createDirectories(path);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    try {
      // import the csv file to the Import folder.
      InputStream inputStream = null;
      OutputStream outputStream = null;
      if (file.getSize() > 0) {
        inputStream = file.getInputStream();
        outputStream = new FileOutputStream(path + file.getOriginalFilename());
        int readBytes = 0;
        byte[] buffer = new byte[8192];
        while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
          outputStream.write(buffer, 0, readBytes);
        }
        outputStream.close();
        inputStream.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importMixDesgin(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    int count = 0;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      List<MixDesignProportion> mixDesignProportions = new ArrayList<MixDesignProportion>();
      // Import the data to DB
      MixDesign mixDesign = new MixDesign();
      MixDesign mixDesignNew = new MixDesign();
      while ((row = csvReader.readNext()) != null) {
        if (mixDesignRepository.findByCode(row[0]) == null) {
          mixDesign.setCode(row[0]);
          mixDesign.setDate(Date.valueOf(row[1].toString()));
          Plant plant = plantRepository.findByName(row[2]);
          mixDesign.setPlant(plant);
          mixDesign.setStatus(Status.valueOf(row[3]));
          RawMaterial rawMaterial = rawMaterialRepository.findByName(row[4]);
          mixDesign.setRawMaterial(rawMaterial);
          mixDesignRepository.save(mixDesign);

        }
        if (mixDesignRepository.findByCode(mixDesign.getCode()) != null) {
          count = count + 1;
          mixDesignNew = mixDesignRepository.findByCode(mixDesign.getCode());
        }
        MixDesignProportion mixDesignProportion = new MixDesignProportion();
        RawMaterial rawMaterial = rawMaterialRepository.findByName(row[5]);
        mixDesignProportion.setRawMaterial(rawMaterial);
        mixDesignProportion.setMixDesign(mixDesignNew);
        mixDesignProportion.setQuantity(Double.valueOf(row[6]));
        Unit unit = unitRepository.findByUnit(row[7]);
        mixDesignProportion.setUnit(unit);
        mixDesignProportions.add(mixDesignProportion);
        mixDesignProportionRepository.save(mixDesignProportion);
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importCustomer(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        if (customerRepository.findByName(row[0]) == null) {
          Customer customer = new Customer();
          List<Plant> plantList = new ArrayList<Plant>();
          customer.setName(row[0]);
          customer.setAddress(row[1]);
          customer.setEmail(row[2]);
          customer.setPhoneNumber(row[3]);
          String[] plants = row[4].split(",");
          for (int i = 0; i < plants.length; i++) {
            Plant plant = plantRepository.findByName(plants[i]);
            plantList.add(plant);
          }
          customer.setPlant(plantList);
          customerRepository.save(customer);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importSupplier(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        // if (supplierRepository.findByEmail(row[0]) == null) {
        Supplier supplier = new Supplier();
        List<SupplierCategory> supplierCategories = new ArrayList<SupplierCategory>();
        supplier.setEmail(row[0]);
        supplier.setName(row[1]);
        supplier.setAddress(row[2]);
        supplier.setPhoneNumber(row[3]);
        Plant plant = plantRepository.findByName(row[4]);
        supplier.setPlant(plant);
        String[] categories = row[5].split(",");
        for (int i = 0; i < categories.length; i++) {
          SupplierCategory supplierCategory =
              supplierCategoryRepository.findByCategory(categories[i]);
          supplierCategories.add(supplierCategory);
        }
        supplier.setSupplierCategories(supplierCategories);
        supplierRepository.save(supplier);
      }
      // }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importEmployee(MultipartFile file, HttpServletRequest request) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        if (employeeRepository.findByEmail(row[0]) == null) {
          Employee employee = new Employee();
          employee.setEmail(row[0]);
          employee.setFirstName(row[1]);
          employee.setLastName(row[2]);
          employee.setAddress(row[3]);
          employee.setPhoneNumber(row[4]);
          Designation designation = designationRepository.findByName(row[5]);
          employee.setDesignation(designation);
          Plant plant = plantRepository.findByName(row[6]);
          employee.setPlant(plant);
          employee.setHasUser(Boolean.FALSE);
          employeeService.createEmployee(employee, request);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importProject(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        if (projectRepository.findByCode(row[0]) == null) {
          Project project = new Project();
          project.setCode(row[0]);
          project.setName(row[1]);
          project.setContactPerson(row[2]);
          project.setContactNumber(row[3]);
          project.setStartDate(Date.valueOf(row[4]));
          Customer customer = customerRepository.findByName(row[5]);
          project.setCustomer(customer);
          Plant plant = plantRepository.findByName(row[6]);
          project.setPlant(plant);
          projectRepository.save(project);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importPlantEquipment(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      PlantEquipment plantEquipment = new PlantEquipment();
      while ((row = csvReader.readNext()) != null) {
        if (plantEquipmentRepository.findPlantEquipmentBySerialNo(row[0]) == null) {
          plantEquipment.setSerialNo(row[0]);
          plantEquipment.setBrandName(row[1]);
          plantEquipment.setCalibrationExists(Boolean.valueOf(row[2]));
          plantEquipment.setDescription(row[3]);
          plantEquipment.setModelName(row[4]);
          Equipment equipment = equipmentRepository.findByName(row[5]);
          plantEquipment.setEquipment(equipment);
          Plant plant = plantRepository.findByName(row[6]);
          plantEquipment.setPlant(plant);
          plantEquipmentRepository.save(plantEquipment);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void importRawMaterial(MultipartFile file, HttpServletRequest request) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        if (rawMaterialRepository.findByName(row[0]) == null) {
          RawMaterial rawMaterial = new RawMaterial();
          rawMaterial.setName(row[0]);
          rawMaterial.setPrefix(row[1]);
          MaterialState materialState = materialStateRepository.findByMaterialState(row[2]);
          rawMaterial.setMaterialState(materialState);
          MaterialSubCategory materialSubCategory =
              materialSubCategoryRepository.findByName(row[3]);
          rawMaterial.setMaterialSubCategory(materialSubCategory);
          Plant plant = plantRepository.findByName(row[4]);
          rawMaterial.setPlant(plant);
          rawMaterial.setDescription(row[5]);
          rawMaterial.setActive(Boolean.TRUE);
          rawMaterialService.saveRawMaterial(rawMaterial);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  @Transactional
  public String storeFile(MultipartFile file)
      throws IOException, TokyoSupermixFileStorageException {
    FileOutputStream fout = new FileOutputStream(new File(file.getOriginalFilename()));
    fout.write(file.getBytes());
    fout.close();
    BufferedImage image = ImageIO.read(new File(file.getOriginalFilename()));
    int height = image.getHeight();
    int width = image.getWidth();
    if (width > 5000 || height > 6000) {
      throw new TokyoSupermixFileStorageException(
          "Invalid file dimensions. File dimension should note be more than 500 X 600");
    }
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if (fileName.contains("..")) {
        throw new TokyoSupermixFileStorageException(
            "Filename contains invalid path sequence" + fileName);
      }
      String newFileName = System.currentTimeMillis() + "_" + fileName;
      Path targetLocation = this.fileStorageLocation.resolve(newFileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
          .path("/downloadFile/").path(newFileName).toUriString();
      return fileDownloadUri;
    } catch (IOException ex) {
      throw new TokyoSupermixFileStorageException(
          String.format("Could not store file %s !! Please try again!", fileName), ex);
    }
  }

  @Transactional
  public String uploadFile(MultipartFile file)
      throws IOException, TokyoSupermixFileStorageException {
    FileOutputStream fout = new FileOutputStream(new File(file.getOriginalFilename()));
    fout.write(file.getBytes());
    fout.close();
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if (fileName.contains("..")) {
        throw new TokyoSupermixFileStorageException(
            "Filename contains invalid path sequence" + fileName);
      }
      String newFileName = System.currentTimeMillis() + "_" + fileName;
      Path targetLocation = this.fileStorageLocation.resolve(newFileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
          .path("/downloadFile/").path(newFileName).toUriString();
      return fileDownloadUri;
    } catch (IOException ex) {
      throw new TokyoSupermixFileStorageException(
          String.format("Could not store file %s !! Please try again!", fileName), ex);
    }
  }

  public boolean isValid(MultipartFile[] multipartFilelist) {
    for (MultipartFile multipartFile : Arrays.asList(multipartFilelist)) {
      if ((multipartFile.getContentType().matches("image/png")
          || multipartFile.getContentType().matches("image/jpeg")
          ||multipartFile.getContentType().matches("image/jpg"))) {
        return true;
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Resource loadFileAsResource(String fileName) throws TokyoSupermixFileNotFoundException {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new TokyoSupermixFileNotFoundException("File Not Found" + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new TokyoSupermixFileNotFoundException("File Not Found" + fileName, ex);
    }
  }

  @Transactional
  public ArrayList<String> importDeliverySample(MultipartFile file) {
    Path path = Paths.get(fileStorageProperties.getUploadDir());
    String csvFilename = path + file.getOriginalFilename();
    ArrayList<String> codeArray = new ArrayList<String>();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] row = null;
    int count = 0;

    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        if (finishProductSampleRepository.findByCode(row[0]) == null) {
          FinishProductSample finishProductSample = new FinishProductSample();
          finishProductSample.setCode(row[0]);
          MixDesign mixDesign = mixDesignRepository.findByCode(row[1]);
          finishProductSample.setMixDesign(mixDesign);
          PlantEquipment plantEquipment =
              plantEquipmentRepository.findPlantEquipmentBySerialNo(row[2]);
          finishProductSample.setPlantEquipment(plantEquipment);
          finishProductSample.setDate(Date.valueOf(row[3]));
          finishProductSample.setFinishProductCode(row[4]);
          finishProductSample.setWorkOrderNumber(row[5]);
          if (row[6].isEmpty()) {
            finishProductSample.setProject(null);
          } else {
            Project project = projectRepository.findByCode(row[6]);
            finishProductSample.setProject(project);
          }
          finishProductSample.setTruckNo(row[7]);
          finishProductSample.setStatus(Status.NEW);
          finishProductSample.setFinishProductSampleType(FinishProductSampleType.DELIVERY_SAMPLE);
          count++;
          finishProductSampleRepository.save(finishProductSample);
        } else {
          codeArray.add(0, String.valueOf(count));
          codeArray.add(row[0]);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (ArrayList<String>) codeArray.stream().distinct().collect(Collectors.toList());
  }
  
  public boolean Valid(MultipartFile multipartFile) {
         if ((multipartFile.getContentType().matches("image/png")
          || multipartFile.getContentType().matches("image/jpeg")
          ||multipartFile.getContentType().matches("image/jpg"))) {
        return true;
      }    
    return false;
  }

}
