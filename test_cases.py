from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
import time

# Initialize the Chrome WebDriver
service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

# Function for Test Case 1: Navigate to Veterans Health Administration and return to Homepage
def test_case_1_vha_navigation(driver):
    driver.get("https://www.va.gov")  # Go to VA.gov homepage
    about_va_menu = WebDriverWait(driver, 30).until(EC.element_to_be_clickable((By.XPATH, "//a[normalize-space()='About VA']")))
    about_va_menu.click()  # Click on 'About VA' menu

    vha_link = WebDriverWait(driver, 30).until(EC.element_to_be_clickable((By.XPATH, "//a[normalize-space()='Veterans Health Administration']")))
    vha_link.click()  # Click on 'Veterans Health Administration' link

    driver.get("https://www.va.gov")  # Return to the homepage

# Function for Test Case 2: Find a VA Location using Zip Code
def test_case_2_find_va_location(driver):
    find_location = WebDriverWait(driver, 30).until(EC.element_to_be_clickable((By.XPATH, "//a[normalize-space()='Find a VA location']")))
    find_location.click()  # Click on 'Find a VA location'

    search_box = WebDriverWait(driver, 30).until(EC.visibility_of_element_located((By.ID, "street-address")))
    search_box.send_keys("75071")  # Enter zip code

    facility_type_dropdown = Select(driver.find_element(By.ID, "facility-type-dropdown"))
    facility_type_dropdown.select_by_visible_text("VA Health")  # Select 'VA Health' facility type

    search_button = driver.find_element(By.XPATH, "//input[@type='submit' and @value='Search']")
    search_button.click()  # Click 'Search' button

    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.XPATH, "//div[@id='facility-locations']")))
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")  # Scroll to the bottom of the page

    driver.get("https://www.va.gov")  # Return to the homepage

# Execute test cases
try:
    test_case_1_vha_navigation(driver)
    test_case_2_find_va_location(driver)
finally:
    driver.quit()  # Close the browser after the tests
