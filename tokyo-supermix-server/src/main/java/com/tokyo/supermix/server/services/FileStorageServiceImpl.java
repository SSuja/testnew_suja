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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
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
  private UnitRepository unitRepository;

  @Transactional
  public void uploadCsv(MultipartFile file) {
    Path path = Paths.get("/tokyo-supermix-server/Upload");
    try {
      Files.createDirectories(path);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
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
    Path path = Paths.get("/tokyo-supermix-server/Upload");
    String csvFilename = path + file.getOriginalFilename();
    // Read the csv file
    CSVReader csvReader = null;
    try {
      csvReader = new CSVReader(new FileReader(csvFilename));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String[] row = null;
    try {
      row = csvReader.readNext();
      row = csvReader.readNext();
      
      // Import the data to DB
      while ((row = csvReader.readNext()) != null) {
        MixDesign mixDesign = new MixDesign();
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
          mixDesignRepository.save(mixDesign);
        }

        if (mixDesignRepository.findByCode(mixDesign.getCode()) != null) {
          MixDesignProportion mixDesignProportion = new MixDesignProportion();
          RawMaterial rawMaterial = rawMaterialRepository.findByName(row[8]);
          rawMaterial.setId(rawMaterial.getId());
          mixDesignProportion.setRawMaterial(rawMaterial);
          mixDesignProportion.setMixDesign(mixDesign);
          mixDesignProportion.setQuantity(Long.valueOf(row[9]));
          Unit unit = unitRepository.findByUnit(row[10]);
          mixDesignProportion.setUnit(unit);
          mixDesignProportionRepository.save(mixDesignProportion);
        }
      }
      csvReader.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
