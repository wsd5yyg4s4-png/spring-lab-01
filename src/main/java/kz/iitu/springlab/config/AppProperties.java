package kz.iitu.springlab.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app")
public record AppProperties(
        @NotBlank String owner,
        @NotBlank String group,
        @Valid Mail mail,
        @Valid Ui ui
) {

    public record Mail(
            @NotBlank
            @Email
            String from,

            @Min(1)
            @Max(10)
            @DefaultValue("3")
            int retryCount,

            @DefaultValue("5s")
            Duration timeout,

            @DefaultValue("true")
            boolean enabled
    ) {
    }

    public record Ui(
            @Pattern(regexp = "LIGHT|DARK")
            @DefaultValue("LIGHT")
            String theme,

            @Min(5)
            @Max(50)
            @DefaultValue("10")
            int itemsPerPage
    ) {
    }
}