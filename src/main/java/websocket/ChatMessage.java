package websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by Dejv on 13.01.17.
 */
@Data
public class ChatMessage {

    public String username;
    public Date created_at;
    public String message;

}
