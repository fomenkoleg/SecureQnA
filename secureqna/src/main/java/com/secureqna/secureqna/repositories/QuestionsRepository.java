package com.secureqna.secureqna.repositories;

import com.secureqna.secureqna.objects.Question;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionsRepository extends JpaRepository<Question,Long> {



}
