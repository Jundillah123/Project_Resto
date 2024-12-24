package Resto.JundiResto.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class UserRespon {
    private String userId;
    private String name;
    private String password;
    private String email;
    private String roll;
    private Timestamp creadtedAt;
    private boolean isDeleted;
    private String createdBy;
}
