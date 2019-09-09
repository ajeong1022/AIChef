# AI Chef

AI Chef is an interactive grocery shopping assistant which suggests recipes to the users based on the ingredients that they scan using the phone's camera. Ingredient scanning is achieved by some basic image classification. The TensorFlow Lite model used for this task was created by performing transfer learning on Google's MobileNet V2 with photos of common fruits and vegetable ingredients. Currently, the application only recognizes fruits and vegetables as a starting point for suggesting recipes.    

# ARM NN (optional)

Part of this project was to investigate the usage of the ARM NN Hardware Abstraction Layer for Android, which provides an efficient way to off-load some of the machine learning computation to ARM Mali GPU's. **ARM NN is not essential to run this application**, but if you would like to experiment with ARM NN for Android in combination with Android's NNAPI, then make sure you have the ARM NN driver installed by following the instructions at https://github.com/Arm-software/android-nn-driver.   

# Installation 

1. Create a RapiAPI account at https://rapidapi.com/spoonacular/api/recipe-food-nutrition/pricing. 
2. Import the repository into Android Studio. 
3. Paste your RapidAPI account key in the strings.xml file in the string element named API_KEY.  
4. Deploy the application to an Android device.

# Usage 

1. Aim the camera at a vegetable ingredient to scan. 
2. Press and hold the scan button on the main page to begin scanning. 
3. If scanning is successful, a dialog will appear asking for confirmation to add said ingredient to a list of scanned ingredients. 
4. Use the slide-up panel in the main activity to manage scanned ingredients and invoke the recipe API to gather recipes. 

A detailed overview of the suggested recipes can be found in the page reachable via the 'RECIPES LIST' navigation button. A cumulative shopping list of ingredients for the selected recipes is available via the 'SHOPPING LIST' navigation button in the recipe overview page. 

# User Interface
<img src="/Screenshots/Scanner.png" width="250" height="450"/> <img src="/Screenshots/SlideUpPanel.png" width="250" height="450"/>

