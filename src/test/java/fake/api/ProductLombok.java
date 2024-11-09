package fake.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLombok {
    private int id;
    private String title;
    private float price;
    private String description;
    private String category;
    private String image;
    private ProductResponse.Rating rating;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Rating{
        private float rate;
        int count;
    }
}
