package ru.fix.models;

import lombok.*;
import org.springframework.stereotype.Component;
import javax.ejb.Singleton;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Information {

   private Integer answer;

}
