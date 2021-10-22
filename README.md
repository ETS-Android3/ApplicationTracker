Original App Design Project - README Template.md
---
Original App Design Project - README Template
===

# Application Tracker

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Application Tracker helps the user maintain a list of job applications and the status of the application. Users can add new applications, edit its details, and delete old applications. Users will have a list of all the applications with company name, job position, and status of application.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Productivity
- **Mobile:**
- **Story:** I believe the app could be very useful. The benefit of having access to all your applications on your phone can help users stay in track with their applications.
- **Market:** Large market opportunity because software engineers can use this app repeatedly to track applications when they are searching for a job
- **Habit:** App is not habit-forming or addictive. An average user will primarily create, but they can also consume.
- **Scope:** The core of the project is simple and can be implemented. However, implementing some of the "stretch goal" features can be hard.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can login/signup to Parse account
* User can see dashboard of all applications
        * Includes date applied, company name, and status of application (accepted, interviewed, rejected)
* User can add new application to dashboard
* User can edit or delete application entry

**Optional Nice-to-have Stories**

* User can navigate to detail view to see more information
    * User can add notes into notebox for each application
        * [Stretch-Stretch] User can add "update notes" to each application, with associated date/timestamp
* App persists application data locally for offline access
* User can filter applications by status
* User can filter applications by date applied
* User can search dashboard by keyword using search bar
* User can set dates/deadlines for each application and be sent a push notification to be reminded
* User can export all app data to CSV
* User can maintain "wishlist" of companies to apply to
* Dashboard view displays company logo fetched from website URL
* User can login with fingerprint/face ID
* User can open Google Maps to physical address of interview

### 2. Screen Archetypes

* Login
   * Users can login
* Register
   * Users can register
* Stream
   * Users can view a list of existing applications created
   * Includes date applied, company name, and status of application (accepted, interviewed, rejected)
* Creation
    * User can create a new application
    * User can edit the application
* Detail
    * User can view details of the application

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Logout
* Stream
* Creation

**Flow Navigation** (Screen to Screen)

* Login
   * stream
* Register
    * stream
* Stream
   * detail
* Creation
    * stream
* Detail
    * stream

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
