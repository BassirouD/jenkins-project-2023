package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    //private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        //autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    /*
        @AfterEach
        void tearDown() throws Exception {
            autoCloseable.close();
        }
    */
    @Test
    void canGetAllStudents() {
        //when
        underTest.getAllStudents();

        //then
        verify(studentRepository).findAll();

    }

    @Test
    void canAddStudent() {
        //given
        Student student = new Student("Diallo", "diallo@mail.com", Gender.FEMALE);

        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student captorStudent = studentArgumentCaptor.getValue();

        assertThat(captorStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        Student student = new Student("Diallo", "diallo@mail.com", Gender.FEMALE);

        given(studentRepository.selectExistsEmail(student.getEmail()))
                .willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());


    }

    @Test
    void deleteStudent() {
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true);
        underTest.deleteStudent(studentId);
        verify(studentRepository, Mockito.times(1)).deleteById(studentId);
    }

    /*
    @Test
    public void testDeleteStudentNotFound() {
        Long studentId = 1L;
        Mockito.when(studentRepository.existsById(studentId)).thenReturn(false);

        assertThatThrownBy(() -> underTest.deleteStudent(studentId))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Student with id " + studentId + " does not exists");

        verify(underTest, never()).deleteStudent(any());
    }*/
}