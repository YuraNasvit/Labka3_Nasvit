package ua.kpi.its.lab.rest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.EducationalInstitutionRequest
import ua.kpi.its.lab.rest.dto.EducationalInstitutionResponse
import ua.kpi.its.lab.rest.dto.DisciplineRequest
import ua.kpi.its.lab.rest.dto.DisciplineResponse
import ua.kpi.its.lab.rest.entity.EducationalInstitution
import ua.kpi.its.lab.rest.entity.Discipline
import ua.kpi.its.lab.rest.svc.EducationalInstitutionService
import ua.kpi.its.lab.rest.svc.DisciplineService

@RestController
class RestApiController @Autowired constructor(
    private val educationalInstitutionService: EducationalInstitutionService,
    private val disciplineService: DisciplineService
) {

    @PostMapping("/educational-institutions")
    fun addEducationalInstitution(@RequestBody request: EducationalInstitutionRequest): ResponseEntity<EducationalInstitutionResponse> {
        val response = educationalInstitutionService.addEducationalInstitution(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/educational-institutions")
    fun getAllEducationalInstitutions(): ResponseEntity<MutableList<EducationalInstitution>> {
        val response = educationalInstitutionService.getAllEducationalInstitutions()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/educational-institutions")
    fun deleteEducationalInstitutionById(@RequestBody id: Long): ResponseEntity<Any> {
        educationalInstitutionService.deleteEducationalInstitutionById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/educational-institutions/disciplines")
    fun addDisciplineToEducationalInstitution(@RequestBody educationalInstitutionId: Long, disciplineId: Long): ResponseEntity<EducationalInstitution?> {
        val response = educationalInstitutionService.addDisciplineToEducationalInstitution(educationalInstitutionId, disciplineId)
        return if (response != null) {
            ResponseEntity(response, HttpStatus.CREATED)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/disciplines")
    fun addDiscipline(@RequestBody request: DisciplineRequest): ResponseEntity<DisciplineResponse> {
        val response = disciplineService.addDiscipline(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/disciplines")
    fun getAllDisciplines(): ResponseEntity<MutableList<Discipline>> {
        val response = disciplineService.getAllDisciplines()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/disciplines")
    fun deleteDisciplineById(@RequestBody id: Long): ResponseEntity<Any> {
        disciplineService.deleteDisciplineById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/disciplines/{id}")
    fun getDisciplineById(@PathVariable id: Long): ResponseEntity<DisciplineResponse> {
        val response = disciplineService.getDisciplineById(id)
        return ResponseEntity(response, HttpStatus.OK)
    }
}