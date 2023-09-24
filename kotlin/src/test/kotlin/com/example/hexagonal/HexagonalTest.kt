package com.example.hexagonal

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.Test

class HexagonalTest {
    @Test
    fun domainShouldNotDependOnAnyOtherPackages() {
        ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAnyPackage("com.example.hexagonal.application.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                "com.example.hexagonal.application.port..",
                "com.example.hexagonal.application.usecase..",
            )
            .because("Domain should not depend on any other packages")
            .check(importedClasses)
    }

    @Test
    fun usecaseAndPortShouldNotDependOnAdapter() {
        ArchRuleDefinition
            .noClasses()
            .that()
            .resideInAnyPackage("com.example.hexagonal.application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.example.hexagonal.adapter..")
            .because("Application should not depend on any adapter packages")
            .check(importedClasses)
    }

    companion object {
        val importedClasses = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.example.hexagonal")
    }
}
