package ua.kpi.its.lab.rest.repository


import org.springframework.data.jpa.repository.JpaRepository
import ua.kpi.its.lab.rest.entity.EducationalInstitution
import ua.kpi.its.lab.rest.entity.Discipline

interface EducationalInstitutionRepository: JpaRepository<EducationalInstitution, Long>
interface DisciplineRepository: JpaRepository<Discipline, Long>