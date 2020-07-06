# CarInsurance
A Car Insurance Management Android application that uses Machine Learning to detect the ***location*** and ***severity*** of damage on cars. The Damage Detection Machine Learning model is made using a _Convolution Neural Network_ and can be found in the `CarDamage.ipynb` notebook. 

The model is trained for **10 classes** -
1. front_major
2. front_moderate
3. front_minor
4. side_major
5. side_moderate
6. side_minor
7. rear_major
8. rear_moderate
9. rear_minor
10. whole

The app provides a **profile login**, and also supports a fully-functional **payment gateway**.
The backend of this project is based on the **Django server**, including _model deployment_. The project makes use of the **MongoDB** database to store details of the user.

The complete backend setup can be found in the following repository:
https://github.com/mr-propya/CarInsurance

Contributors:
[Mahesh Tamse](https://github.com/mr-propya)
