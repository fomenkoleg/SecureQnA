package com.secureqna.secureqna.repositories;

import com.secureqna.secureqna.objects.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answers, Long> {


}
