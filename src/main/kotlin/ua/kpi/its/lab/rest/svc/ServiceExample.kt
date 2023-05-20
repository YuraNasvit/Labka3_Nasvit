package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.dto.EducationalInstitutionRequest
import ua.kpi.its.lab.rest.dto.EducationalInstitutionResponse
import ua.kpi.its.lab.rest.dto.DisciplineRequest
import ua.kpi.its.lab.rest.dto.DisciplineResponse
import ua.kpi.its.lab.rest.entity.EducationalInstitution
import ua.kpi.its.lab.rest.entity.Discipline

interface EducationalInstitutionService {
    fun addEducationalInstitution(request: EducationalInstitutionRequest): EducationalInstitutionResponse
    fun getAllEducationalInstitutions(): MutableList<EducationalInstitution>
    fun deleteEducationalInstitutionById(id: Long)
    fun addDisciplineToEducationalInstitution(educationalInstitutionId: Long, disciplineId: Long): EducationalInstitution?
}

interface DisciplineService {
    fun addDiscipline(request: DisciplineRequest): DisciplineResponse
    fun getAllDisciplines(): MutableList<Discipline>
    fun deleteDisciplineById(id: Long)
    fun getDisciplineById(id: Long): DisciplineResponse
}