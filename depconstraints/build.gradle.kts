/*
 * Copyright 2020 Shreedhar Acharya
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("java-platform")
}

val activity = "1.1.0"
val core = "1.3.2"
val appcompat = "1.2.0"
val material = "1.2.0"
val constraintLayout = "2.0.2"
val junitExt = "1.1.2"
val junit = "4.13"
val espresso = "3.3.0"
val gson = "2.8.6"
val room = "2.2.5"
val retrofit = "2.9.0"
val okhttp = "3.10.0"
val hilt = Versions.HILT
val hiltJetPack = "1.0.0-alpha01"
val timber = "4.7.1"
val glide = "4.10.0"
val lifecycle = "2.2.0"


dependencies {
    constraints {
        api("${Libs.ACTIVITY_KTX}:$activity")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.APPCOMPAT}:$appcompat")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")
        api("${Libs.ESPRESSO_CORE}:$espresso")
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.JUNIT}:$junit")
        api("${Libs.GSON}:$gson")
        api("${Libs.ROOM_KTX}:$room")
        api("${Libs.ROOM_RUNTIME}:$room")
        api("${Libs.ROOM_COMPILER}:$room")
        api("${Libs.RETROFIT_RUNTIME}:$retrofit")
        api("${Libs.RETROFIT_MOCK}:$retrofit")
        api("${Libs.RETROFIT_GSON}:$retrofit")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_TESTING}:$hilt")
        api("${Libs.HILT_VIEWMODEL}:$hiltJetPack")
        api("${Libs.ANDROIDX_HILT_COMPILER}:$hiltJetPack")
        api("${Libs.TIMBER}:$timber")
        api("${Libs.GLIDE_COMPILER}:$glide")
        api("${Libs.GLIDE}:$glide")
        api("${Libs.LIFECYCLE_LIVE_DATA_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_COMPILER}:$lifecycle")
    }
}
