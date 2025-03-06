package ERP.BackEnd_ERP.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResetPasswordRequest {
    private String username;
    private String email;
}
