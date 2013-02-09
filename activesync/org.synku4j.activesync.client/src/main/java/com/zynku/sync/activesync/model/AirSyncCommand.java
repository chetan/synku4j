package com.zynku.sync.activesync.model;

public final class AirSyncCommand {
	public enum CommandType {
		Add,
		Delete,
		Change
	}
	
	private String serverId;
	private CommandType commandType;
	private final ApplicationData appData = new ApplicationData();
	
	public AirSyncCommand() {
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public ApplicationData getAppData() {
		return appData;
	}
}
