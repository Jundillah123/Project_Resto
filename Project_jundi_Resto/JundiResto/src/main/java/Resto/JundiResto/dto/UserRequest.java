package Resto.JundiResto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String password;
    private String email;
    private String roll;
    private LocalDateTime createdAt;
    private String createdBy;
}
