package ua.kpi.its.lab.rest.dto

import ua.kpi.its.lab.rest.entity.Discipline

data class EducationalInstitutionRequest(
    val name: String,
    val accreditationLevel: String,
    val address: String,
    val foundingDate: String,
    val numberOfFaculties: Int,
    val website: String,
    val hasMilitaryDepartment: Boolean,
    var disciplineList: MutableList<Discipline>
)

data class EducationalInstitutionResponse(
    var id: Long,
    var name: String,
    val accreditationLevel: String,
    val address: String
)

data class DisciplineRequest(
    val name: String,
    val specialtyCode: String,
    val semester: Int,
    val hoursCount: Int,
    val approvalDate: String,
    val hasExam: Boolean
)

data class DisciplineResponse(
    var id: Long,
    val name: String,
    val specialtyCode: String
)