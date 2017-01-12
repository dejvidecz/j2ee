package jms;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Dejv on 12.01.17.
 */
@Data
@AllArgsConstructor
public class Email implements Serializable {

    public String receiver;
    public String body;

}
