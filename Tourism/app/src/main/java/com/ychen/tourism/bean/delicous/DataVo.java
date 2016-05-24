package com.ychen.tourism.bean.delicous;

import java.util.Arrays;

/**
 * Created by 72963 on 2015/10/26.
 */
public class DataVo {
    private String id;//"41700",
    private String title;//"涮羊肉",
    private String tags;//"秋季;聚会;开胃;火锅",
    private String imtro;//"涮羊肉又称“羊肉火锅”，行于北京天津的市民美食，提起“涮羊肉”，几乎尽人皆知。因为这道佳肴吃法简便、味道鲜美，所以深受欢迎。涮羊肉的调料是关键。包含了“辛、辣、卤、糟、鲜”的成分，构成了独特的香味。由于传统的铜火锅是用木炭加热的，比较麻烦。一般家庭都改用电火锅了，味道是一样的。我喜欢吃火锅 ，不只是为了满足于口感，而是喜欢一家人围坐在一起，边吃边聊其乐融融的感觉，让人感到很温馨、很幸福。",
    private String ingredients;//"羊肉片,1000g;苦菊,200g;魔芋,200g;百叶,200g;生菜,200g;鱼丸,200g;烧饼,200g;香菜,200g;大虾,200g;小肥羊底料,1袋",
    private String burden;//"香油,适量;芝麻酱,适量;腐乳
    private String[] albums;//"http:\/\/img.juhe.cn\/cookbook\/t
    private StepsVo[] steps;

    public String[] getAlbums() {
        return albums;
    }

    public void setAlbums(String[] albums) {
        this.albums = albums;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public StepsVo[] getSteps() {
        return steps;
    }

    public void setSteps(StepsVo[] steps) {
        this.steps = steps;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DataVo{" +
                "albums=" + Arrays.toString(albums) +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", imtro='" + imtro + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", burden='" + burden + '\'' +
                ", steps=" + Arrays.toString(steps) +
                '}';
    }
}
