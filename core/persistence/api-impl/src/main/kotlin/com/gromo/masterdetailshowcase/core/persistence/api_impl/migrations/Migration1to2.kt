package com.gromo.masterdetailshowcase.core.persistence.api_impl.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration1to2 : Migration(1, 2) {

    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `episode` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `airDate` TEXT NOT NULL, `episode` TEXT NOT NULL, `characters` TEXT NOT NULL, `url` TEXT NOT NULL, PRIMARY KEY(`id`))")
    }
}
