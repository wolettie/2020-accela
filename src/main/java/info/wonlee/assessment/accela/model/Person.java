package info.wonlee.assessment.accela.model;
/***************************************************************
 * Copyright (c) 2020 Errigal Inc.
 *
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 *
 ***************************************************************/

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * User: wonlee
 * Date: 20/12/2020
 */

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(generator = "PERSON_SEQ")
    @SequenceGenerator(name="PERSON_SEQ", sequenceName="PERSON_SEQ", allocationSize = 20)
    private Long id;

    private String firstName;
    private String lastName;
}
