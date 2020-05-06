package com.kovalenko.application.commoncommand;

import com.kovalenko.application.commoncommand.factory.CommonCommandFactory;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.view.render.Renderer;
import com.kovalenko.application.view.render.ViewRenderer;
import com.kovalenko.application.view.view.ConsoleView;

import java.util.Optional;

public class CommonCommandExecutor {

    private Renderer renderer = new ViewRenderer();

    public void execute(ConsoleRequest request) {
        CommonCommandType commonCommandType = CommonCommandType.findCommonCommandType(request.getRequestPath());
        Optional<CommonCommand> command = CommonCommandFactory.getCommand(commonCommandType);
        if (command.isPresent()) {
            ConsoleView commandView = command.get().execute();
            renderer.render(commandView);
        }
    }
}
