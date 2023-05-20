package ua.kpi.its.lab.rest.entity

import jakarta.persistence.*
import java.time.LocalDate

// Сутність, що представляє навчальний заклад з відповідними полями
@Entity
@Table(name = "educational_institutions")
data class EducationalInstitution(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    val name: String, // Назва навчального закладу
    val accreditationLevel: String, // Рівень акредитації
    val address: String, // Адреса навчального закладу
    val foundingDate: String, // Дата заснування
    val numberOfFaculties: Int, // Кількість факультетів
    val website: String, // Веб-сайт навчального закладу
    val hasMilitaryDepartment: Boolean, // Наявність військової кафедри
    @OneToMany(mappedBy = "educationalInstitution", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var disciplineList: MutableList<Discipline> = mutableListOf() // Список дисциплін
) : Comparable<EducationalInstitution> {
    override fun compareTo(other: EducationalInstitution): Int {
    // Сортування за назвою навчального закладу, а якщо назви різні - то за датою заснування.
        return if (this.name == other.name) {
            this.foundingDate.compareTo(other.foundingDate)
        } else {
            this.name.compareTo(other.name)
        }
    }
}

// Сутність, що представляє дисципліну з відповідними полями
@Entity
@Table(name = "disciplines")
data class Discipline(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 1,
    val name: String, // Назва дисципліни
    @ManyToOne()
    @JoinColumn(name = "educational_institution_id")
    var educationalInstitution: EducationalInstitution? = null, // Заклад, до якого належить дисципліна
    val specialtyCode: String, // Код спеціальності
    val semester: Int, // Семестр, в якому викладається дисципліна
    val hoursCount: Int, // Кількість годин
    val approvalDate: String, // Дата затвердження
    val hasExam: Boolean // Наявність екзамену
) : Comparable<Discipline> {
    override fun compareTo(other: Discipline): Int {
        // Сортування за назвою дисципліни, а якщо назви різні - то за датою затвердження.
        return if (this.name == other.name) {
            this.approvalDate.compareTo(other.approvalDate)
        } else {
            this.name.compareTo(other.name)
        }
    }
}
