package altamirano.hernandez.proyectogastos_springboot_angular.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Value("${CLOUDINARY_NAME}")
    private String CLOUDINARY_NAME;
    @Value("${CLOUDINARY_API_KEY}")
    private String CLOUDINARY_API_KEY;
    @Value("${CLOUDINARY_API_SECRET}")
    private String CLOUDINARY_API_SECRET;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET
        ));
    }
}
