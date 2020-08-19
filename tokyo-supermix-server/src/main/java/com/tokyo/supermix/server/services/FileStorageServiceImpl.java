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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.EquipmentRepository;
import com.tokyo.supermix.data.repositories.MaterialCategoryRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
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
  private PlantEquipmentRepository plantEquipmentRepository;
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Transactional
  public void uploadCsv(MultipartFile file) {
    Path path = Paths.get("C://Users/Import");
    // Path path = Paths.get("/home/ubuntu/Import");
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
    Path path = Paths.get("C://Users/Import");
    // Path path = Paths.get("/home/ubuntu/Import");
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
  public void importPlantEquipment(MultipartFile file) {
    Path path = Paths.get("C://Users/Import");
    // Path path = Paths.get("/home/ubuntu/Import");
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
          System.out.println("serialhhh"+row[0]);
          plantEquipment.setBrandName(row[1]);
          plantEquipment.setCalibrationExists(Boolean.valueOf(row[2]));
          plantEquipment.setDescription(row[3]);
          plantEquipment.setModelName(row[4]);
          Equipment equipment = equipmentRepository.findByName(row[5]);
//          equipment.setName(row[5]);
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
