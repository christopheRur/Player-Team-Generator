// /////////////////////////////////////////////////////////////////////////////
// IMPORTANT:
// THE CODE BELOW IS READ-ONLY CODE AND YOU SHOULD INSPECT IT TO SEE WHAT IT
// DOES IN ORDER TO COMPLETE THE TASK, BUT DO NOT MODIFY IT IN ANY WAY AS THAT
// WILL RESULT IN A TEST FAILURE
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.scopic.*"})
@EntityScan(basePackages = {"com.scopic.*"})
@EnableJpaRepositories(basePackages = {"com.scopic.*"})
public class PlayerFinderApplication {

    public static void main(String[] args) {
        _._();
        SpringApplication.run(PlayerFinderApplication.class, args);
    }
}
