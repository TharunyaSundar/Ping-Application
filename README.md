# Ping-Application
This Android application allows users to ping a domain and view the result.

## Features
- Ping a domain to check its reachability and round-trip time.
- View the ping result, including the host, address, and round-trip time.
- Navigate between the main activity, enter domain activity, and result activity.

## Getting Started
To use this application, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on your Android device or emulator.

## USAGE
- Enter Domain Name or IP Address: In the application's main screen, enter the domain name or IP address you want to ping into the text field.
- Perform Ping: Click on the "Ping" button to perform the ping operation. The application will attempt to ping the specified domain or IP address and display the result.
- View Result: After the ping operation is completed, the application will display the result, including whether the ping was successful, the IP address of the domain, and the round-trip time.
- Navigation: You can navigate to the main screen by clicking the "Back" button. Alternatively, you can return to the home screen by clicking the "Home" button.

## TROUBLESHOOTING
- Internet Connection: Ensure that your device or emulator has an active internet connection, as the application requires internet access to perform the ping operation.
- Permissions: The application requires the "INTERNET" permission to perform the ping operation. Make sure that the necessary permissions are granted when prompted.

## Dependencies
- Android SDK
- androidx.appcompat
- com.google.android.material
- com.example.ping
