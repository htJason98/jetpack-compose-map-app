package com.mota.presentation.ui.screens.setting

import com.mota.presentation.R

sealed class SettingItem(val icon: Int, val itemName: String) {
    object Groups : SettingItem(R.drawable.ic_settings_groups, "Device Groups")
    object Subscription : SettingItem(R.drawable.ic_subscription, "Subscriptions")
    object VoiceAssistant : SettingItem(R.drawable.ic_voice, "Voice Assistant")
    object Help : SettingItem(R.drawable.ic_help, "Help")
    object Shop : SettingItem(R.drawable.ic_shop, "Shop Pebblebee")
    object SendLog : SettingItem(R.drawable.ic_send_log, "Send Support Data")
    object PhoneSetting : SettingItem(R.drawable.ic_global, "Phone Global Setting")
}
