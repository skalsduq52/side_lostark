import React, { useState } from 'react';
import './App.css';
import Header from './Header';
import Search from './contents/Search'
import Navigation from './Navigation';
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {

    const [userData, setUserData] = useState(null);

  return (
      <div className="App">
          <BrowserRouter>
              <Header setUserData={setUserData} />
              <Routes>
                  <Route path='/char/:characterName' element={<Search userData={userData} setUserData={setUserData}/>} />
              </Routes>
              <Navigation />
          </BrowserRouter>
      </div>
  );
}

export default App;