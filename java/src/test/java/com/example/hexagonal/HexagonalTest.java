package com.example.hexagonal;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

public class HexagonalTest {

  static final JavaClasses importedClasses = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages("com.example.hexagonal");

  @Test
  public void domainShouldNotDependOnAnyOtherPackages() {
    noClasses()
        .that()
        .resideInAnyPackage("com.example.hexagonal.application.domain..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("com.example.hexagonal.application.port..", "com.example.hexagonal.application.usecase..")
        .because("Domain should not depend on any other packages")
        .check(importedClasses);
  }

  @Test
  public void usecaseAndPortShouldNotDependOnAdapter() {
    noClasses()
        .that()
        .resideInAnyPackage("com.example.hexagonal.application..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("com.example.hexagonal.adapter..")
        .because("Application should not depend on any adapter packages")
        .check(importedClasses);
  }
}
