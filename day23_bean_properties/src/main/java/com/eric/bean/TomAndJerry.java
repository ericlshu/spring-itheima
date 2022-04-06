package com.eric.bean;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 21:18
 * @since jdk-11.0.14
 */
@Data
@Component
// @ConfigurationProperties(prefix = "cartoon")
public class TomAndJerry
{
    private Cat cat;
    private Mouse mouse;

    private CartoonProperties cartoonProperties;

    public TomAndJerry(CartoonProperties cartoonProperties)
    {
        this.cartoonProperties = cartoonProperties;

        cat = new Cat();
        cat.setName(cartoonProperties.getCat() != null && StringUtils.hasText(cartoonProperties.getCat().getName()) ? cartoonProperties.getCat().getName() : "tom");
        cat.setAge(cartoonProperties.getCat() != null && cartoonProperties.getCat().getAge() != null ? cartoonProperties.getCat().getAge() : 3);

        mouse = new Mouse();
        mouse.setName(cartoonProperties.getMouse() != null && StringUtils.hasText(cartoonProperties.getMouse().getName()) ? cartoonProperties.getMouse().getName() : "jerry");
        mouse.setAge(cartoonProperties.getMouse() != null && cartoonProperties.getMouse().getAge() != null ? cartoonProperties.getMouse().getAge() : 4);
    }

    public void play()
    {
        System.out.println(cat.getAge() + "岁的" + cat.getName() + "和" + mouse.getAge() + "岁的" + mouse.getName() + "打起来了");
    }
}
