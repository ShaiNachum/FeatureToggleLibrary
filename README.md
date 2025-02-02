# FeatureToggleLibrary 📱

A lightweight, customizable Android library for managing feature toggles in your applications. Enable/disable features dynamically, implement A/B testing, and control feature rollouts with ease.

## Features 🎯
- Create and manage feature toggles
- Schedule features with start/end dates
- Get active features list
- Update feature properties
- Delete individual or all features
- Simple callback-based API

## Installation 🛠️

Add JitPack repository to your root `build.gradle`:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add dependency to your app's `build.gradle`:
```gradle
dependencies {
    implementation 'com.github.YourUsername:FeatureToggleLibrary:1.0.0'
}
```

## Usage 💻

### Create a Feature
```java
FeatureToggle.createFeature(
    context,
    "dark_mode",                  // Feature name
    "Enable dark mode theme",     // Description
    "2024-01-01 00:00:00",       // Start date
    "2024-12-31 23:59:59",       // End date
    new FeatureToggle.Callback_Data<String>() {
        @Override
        public void data(String featureId) {
            if (featureId != null) {
                // Success: featureId contains the new feature's ID
            } else {
                // Error creating feature
            }
        }
    }
);
```

### Get Active Features
```java
FeatureToggle.getActiveFeatures(context, new FeatureToggle.Callback_Data<List<Feature>>() {
    @Override
    public void data(List<Feature> features) {
        if (features != null) {
            for (Feature feature : features) {
                // Process each active feature
            }
        }
    }
});
```

### Get All Features
```java
FeatureToggle.getAllFeatures(context, new FeatureToggle.Callback_Data<List<Feature>>() {
    @Override
    public void data(List<Feature> features) {
        if (features != null) {
            for (Feature feature : features) {
                // Process each feature
            }
        }
    }
});
```

### Get Feature by ID
```java
FeatureToggle.getFeatureById(context, "feature-id", new FeatureToggle.Callback_Data<Feature>() {
    @Override
    public void data(Feature feature) {
        if (feature != null) {
            // Feature found, process it
        }
    }
});
```

### Get Features by Date
```java
FeatureToggle.getFeaturesByDate(context, "2024-01-26", new FeatureToggle.Callback_Data<List<Feature>>() {
    @Override
    public void data(List<Feature> features) {
        if (features != null) {
            // Process features active on specific date
        }
    }
});
```

### Update Feature Dates
```java
FeatureToggle.updateFeatureDates(
    context,
    "feature-id",
    "2024-02-01 00:00:00",      // New start date
    "2024-12-31 23:59:59",      // New end date
    new FeatureToggle.Callback_Data<String>() {
        @Override
        public void data(String result) {
            if (result != null) {
                // Update successful
            }
        }
    }
);
```

### Update Feature Name
```java
FeatureToggle.updateFeatureName(
    context,
    "feature-id",
    "new-name",
    new FeatureToggle.Callback_Data<String>() {
        @Override
        public void data(String result) {
            if (result != null) {
                // Name update successful
            }
        }
    }
);
```

### Delete Feature
```java
FeatureToggle.deleteFeature(
    context,
    "feature-id",
    new FeatureToggle.Callback_Data<String>() {
        @Override
        public void data(String result) {
            if (result != null) {
                // Deletion successful
            }
        }
    }
);
```

### Delete All Features
```java
FeatureToggle.deleteAllFeatures(context, new FeatureToggle.Callback_Data<String>() {
    @Override
    public void data(String result) {
        if (result != null) {
            // All features deleted successfully
        }
    }
});
```

## Model 📋

### Feature
```java
public class Feature {
    private String _id;
    private String name;
    private String description;
    private String beginning_date;
    private String expiration_date;
    private String created_at;
    private String updated_at;
    
    // Getters and setters included
}
```

## Error Handling ⚠️

All methods use callbacks with null checks for error handling:
- Success: callback returns the expected data
- Failure: callback returns null

Example error handling:
```java
FeatureToggle.getFeatureById(context, "feature-id", new FeatureToggle.Callback_Data<Feature>() {
    @Override
    public void data(Feature feature) {
        if (feature != null) {
            // Success: process feature
            processFeature(feature);
        } else {
            // Error: handle failure
            handleError("Failed to get feature");
        }
    }
});
```

## API Overview 🌐

| Method | Description |
|--------|-------------|
| `createFeature()` | Creates a new feature toggle |
| `getActiveFeatures()` | Gets currently active features |
| `getAllFeatures()` | Gets all features |
| `getFeatureById()` | Gets a specific feature by ID |
| `getFeaturesByDate()` | Gets features active on a specific date |
| `updateFeatureDates()` | Updates feature start/end dates |
| `updateFeatureName()` | Updates feature name |
| `deleteFeature()` | Deletes a specific feature |
| `deleteAllFeatures()` | Deletes all features |

## Date Formats ⏰

The library expects dates in the following format:
- For precise timing: `"YYYY-MM-DD HH:MM:SS"`
- For date queries: `"YYYY-MM-DD"`

## Requirements 📱

- Android API level 26 or higher
- Java 11 or higher

## Permissions 🔒

Add to your AndroidManifest.xml:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Contributing 🤝

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support 💬

For issues, questions, or contributions:
1. Check existing issues or create a new one
2. Open a pull request for fixes or improvements
3. Contact the maintainers for urgent matters

---
Made with ❤️ for Android developers
