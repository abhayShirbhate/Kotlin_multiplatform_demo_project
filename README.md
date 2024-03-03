# Kotlin Multiplatform Project: User List

## Overview
This project is a Kotlin Multiplatform project that demonstrates various features including displaying a list of users, allowing users to mark a user as a favorite locally, and implementing a home page with bottom navigation.

## Features
1. **List of Users:**
   - Fetches a list of users from a paginated API (https://gorest.co.in/public/v2/users?page=1&per_page=10).
   - Displays users with their Name, Gender, and Status (active or in-active).
   - Indicates the status of each user with a round icon in the top left corner. Active users have a green icon, while in-active users have a red icon.
   - List item decorations, fonts, and styles can be customized.

2. **Make a User Your Favorite (Local Only):**
   - Allows users to mark a user as a favorite locally.
   - Swipe functionality in the list triggers an animation to indicate making the user a favorite.
   - Favorites are stored locally in a local database.

3. **Home Page UI:**
   - Features a home page with bottom navigation.
   - Bottom Navigation includes 4 menu items: Users, Favorite, To-Do, Profile.
   - Users: Displays all users.
   - Favorite: Displays all favorite users.
   - To-Do and Profile: Placeholder pages for future development.

## How to Run the Application
1. Clone this repository to your local machine.
2. Open the project in your preferred Kotlin Multiplatform IDE.
3. Build and run the application on the desired platform.

## Note
- This project is a demonstration of Kotlin Multiplatform capabilities and showcases various features including API integration, local storage, and UI design.
- Feel free to explore the codebase for implementation details and customization options.

## Acknowledgments
This project was created as a part of a test and serves as an example of a Kotlin Multiplatform project. We thank all contributors who participated in its development.

---
**Note**: This README provides an overview of the Kotlin Multiplatform User List project and its features. For detailed information and implementation details, please refer to the project codebase.
