package laoc.site.effective.builderPattern;

/**
 * Builder模式
 */
public class NutritionFacts {
    private final String name;
    private final int size;
    private final int fat;
    private final int sodium;

    private NutritionFacts(Builder builder){
        this.name = builder.name;
        this.size = builder.size;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
    }

    public static class Builder{
        private final String name;
        private final int size;

        private final int fat = 0;
        private final int sodium = 0;

        public Builder(String name,int size){
            this.name = name;
            this.size = size;
        }

        public Builder fat(int fat){
            fat = fat;
            return this;
        }

        public Builder sodium(int sodium){
            sodium = sodium;

            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }

    public static void main(String[] args){
        NutritionFacts nutritionFacts = new NutritionFacts.Builder("测试",100)
                .fat(20)
                .sodium(100)
                .build();
    }
}
