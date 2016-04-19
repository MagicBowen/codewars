module MultiTap where

import Test.Hspec

presses :: String -> Int
presses = error "to do"

main = hspec $ do
  describe "presses" $ do
    it "should work for simple examples" $ do
      presses "LOL" `shouldBe` 9
    it "should work for phrases with spaces" $ do
      presses "HOW R U" `shouldBe` 13

