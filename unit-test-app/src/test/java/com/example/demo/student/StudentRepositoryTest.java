package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        //given
        String email = "diallo@mail.com";
        Student student = new Student(
                "Diallo", email, Gender.FEMALE);
        underTest.save(student);

        //when
        Boolean existsEmail = underTest.selectExistsEmail(email);

        //then
        assertThat(existsEmail).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        //given
        String email = "diallo@mail.com";

        //when
        Boolean existsEmail = underTest.selectExistsEmail(email);

        //then
        assertThat(existsEmail).isFalse();
    }
}