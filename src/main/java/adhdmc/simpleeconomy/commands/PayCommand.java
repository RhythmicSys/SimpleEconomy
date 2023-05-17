package adhdmc.simpleeconomy.commands;

import adhdmc.simpleeconomy.SimpleEconomy;
import adhdmc.simpleeconomy.util.Parsing;
import adhdmc.simpleeconomy.util.Message;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PayCommand implements TabExecutor {

    Economy economy = SimpleEconomy.getEconomy();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendRichMessage(Message.ONLY_PLAYER_COMMAND.getMessage());
            return false;
        }
        if (args.length < 2) {
            player.sendMessage(Parsing.parsePrefixUsage(Message.ERROR_INCOMPLETE_COMMAND.getMessage(), Message.PAY_USAGE.getMessage()));
            return false;
        }
        String firstArg = args[0];
        String secondArg = args[1];
        OfflinePlayer testTargetPlayer = SimpleEconomy.getInstance().getServer().getOfflinePlayer(firstArg);
        if (!testTargetPlayer.hasPlayedBefore()) {
            Component targetName = SimpleEconomy.getMiniMessage().deserialize(firstArg);
            player.sendMessage(Parsing.parsePrefixTarget(Message.ERROR_PLAYER_INVALID.getMessage(), targetName));
            return false;
        }
        Player targetPlayer = (Player) testTargetPlayer;
        double paymentAmount;
        try {
            paymentAmount = Double.parseDouble(secondArg);
        } catch (NumberFormatException|NullPointerException e) {
            player.sendMessage(Parsing.parsePrefixUsage(Message.ERROR_PAYMENT_MUST_BE_NUMBER.getMessage(), Message.PAY_USAGE.getMessage()));
            return false;
        }
        double initiatorBalance = economy.getBalance(player);
        if (paymentAmount < 0) {
            player.sendMessage(Parsing.parsePrefixOnly(Message.ERROR_PAY_NEGATIVE.getMessage()));
            return false;
        }
        if (paymentAmount > initiatorBalance) {
            player.sendMessage(Parsing.parsePrefixInput(Message.ERROR_NOT_ENOUGH_FUNDS.getMessage(), secondArg));
            return false;
        }
        economy.withdrawPlayer(player, paymentAmount);
        economy.depositPlayer(targetPlayer, paymentAmount);
        player.sendMessage(Parsing.parseTargetValueCurrency(Message.YOU_PAID_TARGET.getMessage(), String.valueOf(paymentAmount), targetPlayer.displayName()));
        if (targetPlayer.isOnline()) {
            targetPlayer.sendMessage(Parsing.parseInitiatorValueCurrency(Message.INITIATOR_PAID_YOU.getMessage(), String.valueOf(paymentAmount), player.displayName()));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
