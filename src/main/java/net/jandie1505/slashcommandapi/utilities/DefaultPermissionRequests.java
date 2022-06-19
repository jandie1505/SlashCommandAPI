package net.jandie1505.slashcommandapi.utilities;

import net.dv8tion.jda.api.Permission;
import net.jandie1505.slashcommandapi.interfaces.SlashCommandPermissionRequest;

import java.util.Collection;
import java.util.List;

public class DefaultPermissionRequests {

    private DefaultPermissionRequests() {}

    /**
     * Returns a SlashCommandPermissionRequest which is always false.
     * This is useless because no one can use the command.
     * @return SlashCommandPermissionRequest
     */
    public static SlashCommandPermissionRequest alwaysNoPermission() {
        return interaction -> false;
    }

    /**
     * Returns a SlashCommandPermissionRequest which is always true.
     * You can use this for public commands where no permission is required.
     * @return SlashCommandPermissionRequest
     */
    public static SlashCommandPermissionRequest publicCommand() {
        return interaction -> true;
    }

    /**
     * Returns a SlashCommandPermissionRequest which checks if the member has specified permissions.
     * @param p Permissions the member must have
     * @return SlashCommandPermissionRequest
     */
    public static SlashCommandPermissionRequest withPermissions(Permission... p) {
        return interaction -> interaction.getMember() != null && interaction.getMember().hasPermission(p);
    }

    /**
     * Returns a SlashCommandPermissionRequest which checks if the member has specified permissions.
     * @param p Collection of permission the member must have
     * @return SlashCommandPermissionRequest
     */
    public static SlashCommandPermissionRequest withPermissions(Collection<Permission> p) {
        return interaction -> interaction.getMember() != null && interaction.getMember().hasPermission(p);
    }

    /**
     * Returns a SlashCommandPermissionRequest which checks if the user has all permissions minimum one of the inner lists.
     * @param permissions List of a List of permissions
     * @return SlashCommandPermissionRequest
     */
    public static SlashCommandPermissionRequest withAdvancedPermissionSystem(List<List<Permission>> permissions) {
        return interaction -> {
            if(interaction.getMember() != null) {
                for(List<Permission> andPermissions : permissions) {
                    if(interaction.getMember().hasPermission(andPermissions)) {
                        return true;
                    }
                }
            }
            return false;
        };
    }
}
