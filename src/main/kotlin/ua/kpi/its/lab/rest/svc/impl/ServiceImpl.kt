package ua.kpi.its.lab.rest.svc.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.kpi.its.lab.rest.dto.EducationalInstitutionRequest
import ua.kpi.its.lab.rest.dto.EducationalInstitutionResponse
import ua.kpi.its.lab.rest.dto.DisciplineRequest
import ua.kpi.its.lab.rest.dto.DisciplineResponse
import ua.kpi.its.lab.rest.entity.EducationalInstitution
import ua.kpi.its.lab.rest.entity.Discipline
import ua.kpi.its.lab.rest.repository.EducationalInstitutionRepository
import ua.kpi.its.lab.rest.repository.DisciplineRepository
import ua.kpi.its.lab.rest.svc.EducationalInstitutionService
import ua.kpi.its.lab.rest.svc.DisciplineService

@Service
class EducationalInstitutionServiceImpl @Autowired constructor (
    private val educationalInstitutionRepository: EducationalInstitutionRepository,
    private val disciplineRepository: DisciplineRepository
): EducationalInstitutionService {
    @Transactional
    override fun addEducationalInstitution(request: EducationalInstitutionRequest): EducationalInstitutionResponse {
        val list = mutableListOf<Discipline>()
        val entity = EducationalInstitution(name = request.name, accreditationLevel = request.accreditationLevel, address = request.address, foundingDate = request.foundingDate, numberOfFaculties = request.numberOfFaculties, website = request.website, hasMilitaryDepartment = request.hasMilitaryDepartment, disciplineList = list)
        val result = educationalInstitutionRepository.save(entity)
        return EducationalInstitutionResponse(result.id, result.name, result.accreditationLevel, result.address)
    }
    @Transactional
    override fun getAllEducationalInstitutions(): MutableList<EducationalInstitution> {
        return educationalInstitutionRepository.findAll()
    }
    @Transactional
    override fun deleteEducationalInstitutionById(id: Long) {
        educationalInstitutionRepository.deleteById(id)
    }
    @Transactional
    override fun addDisciplineToEducationalInstitution(educationalInstitutionId: Long, disciplineId: Long): EducationalInstitution? {
        val educationalInstitution = educationalInstitutionRepository.findById(educationalInstitutionId).orElse(null)
        return if (educationalInstitution != null) {
            val discipline = disciplineRepository.findById(disciplineId).orElse(null)
            if (discipline != null) {
                educationalInstitution.disciplineList.add(discipline)
                discipline.educationalInstitution = educationalInstitution
                disciplineRepository.save(discipline)
                educationalInstitutionRepository.save(educationalInstitution)
            } else {
                null
            }
        } else {
            null
        }
    }
}

@Service
class DisciplineServiceImpl @Autowired constructor (
    private val disciplineRepository: DisciplineRepository
): DisciplineService {

    override fun addDiscipline(request: DisciplineRequest): DisciplineResponse {
        val entity = Discipline(name = request.name, specialtyCode = request.specialtyCode, semester = request.semester, hoursCount = request.hoursCount, approvalDate = request.approvalDate, hasExam = request.hasExam)
        val result = disciplineRepository.save(entity)
        return DisciplineResponse(result.id, result.name, result.specialtyCode)
    }

    override fun getAllDisciplines(): MutableList<Discipline> {
        return disciplineRepository.findAll()
    }

    override fun deleteDisciplineById(id: Long) {
        disciplineRepository.deleteById(id)
    }

    override fun getDisciplineById(id: Long): DisciplineResponse {
        val result = disciplineRepository.findById(id).orElse(null)
        return DisciplineResponse(result.id, result.name, result.specialtyCode)
    }
}