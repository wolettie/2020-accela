package info.wonlee.assessment.accela.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Result object is useful for REST API, especially when we need to tell user what went wrong
 * User: wonlee
 * Date: 21/12/2020
 */

@Data
@Builder
public class CommandResult<T> {
    private T result;
    private String errorMessage;
}
