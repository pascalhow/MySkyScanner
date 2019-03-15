package com.pascalhow.myskyscanner.activities.toolbar

interface ToolbarContract {

    interface View {
        fun displayToolbarTitle()
        fun displayToolbarSubtitle()
    }

    interface Presenter {
        fun startPresenting()
        fun buildTitle(): String
        fun buildSubtitle(): String
        fun stopPresenting()
    }

}
