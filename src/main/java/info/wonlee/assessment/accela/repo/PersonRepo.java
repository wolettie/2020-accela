package info.wonlee.assessment.accela.repo;
/***************************************************************
 * Copyright (c) 2020 Errigal Inc.
 *
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 *
 ***************************************************************/

import info.wonlee.assessment.accela.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

public interface PersonRepo extends JpaRepository<Person, Long> {
}
