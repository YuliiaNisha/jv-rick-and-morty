package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyResponseDataDto {
    private ResponseInfoDto info;
    @JsonProperty("results")
    private List<ResponseCharacterDto> characters;
}
