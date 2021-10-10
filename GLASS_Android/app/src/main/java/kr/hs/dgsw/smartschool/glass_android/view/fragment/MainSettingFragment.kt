package kr.hs.dgsw.smartschool.glass_android.view.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import kr.hs.dgsw.smartschool.glass_android.R

class MainSettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        val jobPreference: ListPreference? = findPreference("job")
        val emailPreference: EditTextPreference? = findPreference("email")
        val pwPreference: EditTextPreference? = findPreference("password")

        jobPreference?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()
        emailPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if (TextUtils.isEmpty(text)) {
                    "설정이 되지 않았습니다."
                } else {
                    "설정된 이메일 값은 : $text 입니다."
                }
            }

        pwPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if (TextUtils.isEmpty(text)) {
                    "설정이 되지 않았습니다."
                } else {
                    "설정된 비밀번호 값은 : $text 입니다."
                }
            }
    }

}