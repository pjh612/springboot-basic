package com.voucher.vouchermanagement.manager.command;

import java.util.Arrays;

public enum CommandType {
    CREATE("create", "Type create to create a new voucher."),
    LIST("list", "Type list to list all vouchers."),
    BLACKLIST("blacklist", "Type blacklist to list all blacklist"),
    EXIT("exit", "Type exit to exit program.");

    private String commandName;
    private String commandDescription;

    CommandType(String commandName, String commandDescription) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public static CommandType getCommandTypeByName(String nameInput) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.getCommandName().equals(nameInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드 입력 입니다."));
    }
}
