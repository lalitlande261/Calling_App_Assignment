# 📱 Calling App (Android Internship Assignment)

## 🎯 Objective
This project is a simple Android calling application built using Kotlin and Jetpack Compose.  
It demonstrates real phone call functionality using Android APIs along with contacts and call logs integration.

---

## 🚀 Features

### 📞 Dialer
- Numeric keypad (0–9, *, #)
- Enter phone number
- Make real phone calls using `Intent.ACTION_CALL`
- Runtime permission handling

---

### 📋 Call Logs
- Displays real device call logs
- Shows:
  - Contact name (if available)
  - Phone number
  - Call type (Incoming / Outgoing / Missed)
  - Date & Time
  - Call duration
- Tap any log → call again

---

### 👤 Contacts
- Fetches real device contacts
- Displays name and number
- Tap contact → direct call

---


### 🔐 Permissions
- CALL_PHONE
- READ_CONTACTS
- READ_CALL_LOG
- READ_PHONE_STATE

All permissions are handled at runtime.

---

## 🛠️ Tech Stack

- **Language:** Kotlin  
- **UI:** Jetpack Compose  
- **Architecture:** MVVM  
- **APIs Used:**
  - TelephonyManager
  - CallLog.Calls
  - ContactsContract
  - Intent.ACTION_CALL

---

## 📱 How It Works

1. User enters number → initiates real call
2. Call state is monitored using TelephonyManager
3. Timer runs during active call
4. After call ends → call logs are refreshed
5. Contacts & logs allow direct calling

---
<img width="1080" height="2400" alt="Screenshot_20260328_012027" src="https://github.com/user-attachments/assets/81039301-32fc-40e1-876d-59ef3af5f702" />

<img width="1080" height="2400" alt="Screenshot_20260328_011913" src="https://github.com/user-attachments/assets/ed0c8226-8c38-4987-be5d-d2c110fb70de" />


## 📦 APK

(https://drive.google.com/file/d/1vlmsuF1Rf4dWf2qTQWXrMS1eNLanaUf8/view?usp=sharing)

---

## 📌 Note
- This app uses real device calling features.
- Tested on a physical Android device.
- No fake or simulated call UI is used.

---

## 👨‍💻 Author
**Lalit Lande**
