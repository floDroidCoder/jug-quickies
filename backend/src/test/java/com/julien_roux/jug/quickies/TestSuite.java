package com.julien_roux.jug.quickies;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.julien_roux.jug.quickies.brainfuck.BrainfuckEngineTest;
import com.julien_roux.jug.quickies.controller.QuickyControllerTest;
import com.julien_roux.jug.quickies.controller.UserControllerTest;
import com.julien_roux.jug.quickies.repository.model.QuickyTest;
import com.julien_roux.jug.quickies.repository.model.UserTest;
import com.julien_roux.jug.quickies.repository.model.dto.QuickyDTOTest;
import com.julien_roux.jug.quickies.repository.model.dto.UserDTOTest;
import com.julien_roux.jug.quickies.repository.mongodb.QuickyRepositoryMongoDbTest;
import com.julien_roux.jug.quickies.repository.mongodb.UserRepositoryMongoDbTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   QuickyControllerTest.class,
   UserControllerTest.class,
   QuickyDTOTest.class,
   UserDTOTest.class,
   QuickyTest.class,
   UserTest.class,
   QuickyRepositoryMongoDbTest.class,
   UserRepositoryMongoDbTest.class,
   BrainfuckEngineTest.class
})
public class TestSuite {

}
