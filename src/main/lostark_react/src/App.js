import React, { useState } from 'react';
import './App.css';
import Header from './Header';
import Search from './contents/Search'
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {

    const [userData, setUserData] = useState(null);

  return (
      <div className="App">
          <BrowserRouter>
              <Header setUserData={setUserData} />
              <Routes>
                  <Route path='/loa/:characterName' element={<Search userData={userData} setUserData={setUserData}/>} />
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;