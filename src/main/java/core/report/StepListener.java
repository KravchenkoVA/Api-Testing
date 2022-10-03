package core.report;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepListener implements StepLifecycleListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Writes step result to log
     * @param result - step result (allure)
     */
    @Override
    public void afterStepStart(StepResult result) {
        logger.info(result.getName());
    }
}
