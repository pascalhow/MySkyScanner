package com.pascalhow.myskyscanner.activities.toolbar

import java.util.*

interface ToolbarContract {

    interface View {
        fun displayToolbarTitle()
        fun displayToolbarSubtitle()
    }

    interface Presenter {
        fun startPresenting()
        fun buildTitle(): String
        fun buildSubtitle(calendar: Calendar): String
        fun stopPresenting()
    }

}
