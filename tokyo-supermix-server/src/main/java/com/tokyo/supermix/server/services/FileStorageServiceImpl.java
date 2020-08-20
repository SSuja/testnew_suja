package com.tokyo.supermix.server.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.tokyo.supermix.config.FileStorageProperties;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import com.tokyo.supermix.data.repositories.DesignationRepository;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.MaterialCategoryRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.ProjectRepository;
import com.tokyo.supermix.data.repositories.EquipmentRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.SupplierCategoryRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;
import com.tokyo.supermix.data.repositories.UnitRepository;

@Service
public class FileStorageServiceImpl implements FileStorageService {

  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MaterialCategoryRepository materialCategoryRepository;
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
  private FileStorageProperties fileStorageProperties;

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
          mixDesign.setTargetGrade(Double.valueOf(row[2]));
          mixDesign.setTargetSlump(Double.valueOf(row[3]));
          mixDesign.setWaterBinderRatio(Double.valueOf(row[4]));
          mixDesign.setWaterCementRatio(Double.valueOf(row[5]));
          Plant plant = new Plant();
          plant.setCode((row[6]));
          mixDesign.setPlant(plant);
          mixDesign.setStatus(Status.valueOf(row[7]));
          MaterialCategory materialCategory = materialCategoryRepository.findByName(row[8]);
          mixDesign.setMaterialCategory(materialCategory);
          mixDesignRepository.save(mixDesign);

        }
        if (mixDesignRepository.findByCode(mixDesign.getCode()) != null) {
          count = count + 1;
          mixDesignNew = mixDesignRepository.findByCode(mixDesign.getCode());
        }
        MixDesignProportion mixDesignProportion = new MixDesignProportion();
        RawMaterial rawMaterial = rawMaterialRepository.findByName(row[9]);
        mixDesignProportion.setRawMaterial(rawMaterial);
        mixDesignProportion.setMixDesign(mixDesignNew);
        mixDesignProportion.setQuantity(Long.valueOf(row[10]));
        Unit unit = unitRepository.findByUnit(row[11]);
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
        if (supplierRepository.findByEmail(row[0]) == null) {
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
      }
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
          Plant plant = new Plant();
          plant.setCode((row[6]));
          plantEquipment.setPlant(plant);
          plantEquipmentRepository.save(plantEquipment);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
